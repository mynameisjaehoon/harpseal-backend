package mangmae.harpseal.domain.attachment;


import lombok.RequiredArgsConstructor;
import mangmae.harpseal.domain.attachment.exception.QuestionDataStoreFailException;
import mangmae.harpseal.entity.Attachment;
import mangmae.harpseal.entity.Question;
import mangmae.harpseal.entity.type.AttachmentType;
import mangmae.harpseal.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final FileUtil filePathUtil;

    public Attachment save(final Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    /**
     * 전달받은 첨부파일은 데이터베이스에 저장하는 메서드
     * - 타입을 분석한다.
     * - 타입이 이미지일 경우 storeAttachmentImage 호출
     * - 타입이 소리일 경우 storeAttachmentSound 호출
     * @param type 첨부파일의 타입
     * @param file 첨부파일 MultipartFile
     * @return
     */
    public Optional<Attachment> storeAttachment(
        final Question question,
        final AttachmentType type,
        final MultipartFile file
    ) {
        if (file.isEmpty() || type == AttachmentType.NONE) {
            return Optional.empty();
        }

        String savedFilePath = null;
        if (type == AttachmentType.IMAGE) {
            savedFilePath = storeAttachmentImage(file);
        }
        if (type == AttachmentType.SOUND) {
            savedFilePath = storeAttachmentSound(file);
        }

        // Attachment를 데이터베이스에 저장
        Attachment newAttachment = new Attachment(type, savedFilePath);
        Attachment savedAttachment = save(newAttachment);
        question.addAttachment(savedAttachment); // 연관관계 바인딩

        return Optional.of(savedAttachment);
    }

    private String storeAttachmentSound(final MultipartFile questionSound) {
        if (questionSound.isEmpty()) {
            throw new QuestionDataStoreFailException("There is no sound data to save.");
        }

        String soundPath = filePathUtil.makeQuestionSoundPath();
        try {
            questionSound.transferTo(new File(soundPath));
        } catch (IOException e) {
            throw new QuestionDataStoreFailException("Fail to store question sound data.", e);
        }

        return soundPath;
    }

    private String storeAttachmentImage(final MultipartFile questionImage) {
        if (questionImage.isEmpty()) {
            throw new QuestionDataStoreFailException("There is no image data to save.");
        }

        String imagePath = filePathUtil.makeQuestionImagePath();
        try {
            questionImage.transferTo(new File(imagePath));
        } catch (IOException e) {
            throw new QuestionDataStoreFailException("Fail to store question image data.", e);
        }

        return imagePath;
    }

}

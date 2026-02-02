package kyj.area.teamprofile.domain.member.service;

import io.awspring.cloud.s3.S3Template;
import kyj.area.teamprofile.common.exception.ErrorEnum;
import kyj.area.teamprofile.common.exception.ServiceErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageS3Service {
    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7); // 7일 유지

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final Long maxSize = 10 * 1024 * 1024L; // 10MB
    private final List<String> availableFileTypeList = List.of("image/jpeg", "image/jpg", "image/png");

    public String getUploadKey(MultipartFile file) {
        if(file.getSize() > maxSize) {
            throw new ServiceErrorException(ErrorEnum.ERR_BIGGER_10MB_IMAGE);
        }

        if(!availableFileTypeList.contains(file.getContentType()) ) {
            throw new ServiceErrorException(ErrorEnum.ERR_UNSUPPORTED_IMAGE);
        }

        try {
            String imageKey = "profile-image/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, imageKey, file.getInputStream());
            return imageKey;
        } catch (IOException e) {
            throw new ServiceErrorException(ErrorEnum.ERR_UPLOAD_IMAGE);
        }
    }

    public URL getDownloadUrl(String imageKey) {
        return s3Template.createSignedGetURL(bucket, imageKey, PRESIGNED_URL_EXPIRATION);
    }
}

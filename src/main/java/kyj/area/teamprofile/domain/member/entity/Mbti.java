package kyj.area.teamprofile.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import kyj.area.teamprofile.common.exception.ErrorEnum;
import kyj.area.teamprofile.common.exception.ServiceErrorException;

public enum Mbti {
    INTJ
    , INTP
    , INFJ
    , INFP
    , ISTJ
    , ISTP
    , ISFJ
    , ISFP
    , ENTJ
    , ENTP
    , ENFJ
    , ENFP
    , ESTJ
    , ESTP
    , ESFJ
    , ESFP;

    @JsonCreator
    public static Mbti from(String value) {
        for(Mbti mbti : Mbti.values()) {
            if(mbti.name().equals(value)) {
                return mbti;
            }
        }

        throw new ServiceErrorException(ErrorEnum.ERR_NOT_FOUND_MBTI);
    }
}

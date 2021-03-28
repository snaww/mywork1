package org.csu.mywork1.persistence;

import org.csu.mywork1.domain.SignUp;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpMapper {

    void addSignUp(SignUp signUp);
}

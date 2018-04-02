package com.dari.astro.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dari.astro.bos.LoginUserDetails;
import com.dari.astro.bos.SignUpUser;
import com.dari.astro.queries.DariAstroQueries;
import com.dari.astro.utils.LoginUser;
import com.dari.astro.utils.LogoutUser;
import com.dari.astro.utils.UpdatePassword;

@Repository("dariAstroRepo")
public class DariAstroRepo {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public SignUpUser getSignUpContactByEmailId(SignUpUser signUpUser) {

		SignUpUser SignUpUsertList = (SignUpUser) hibernateTemplate
				.find(DariAstroQueries.SIGNUP_QUERY_BY_EMAILID, new Object[] { signUpUser.getEmailId() }).get(0);
		hibernateTemplate.flush();
		hibernateTemplate.clear();

		return SignUpUsertList;
	}

	public SignUpUser getSignUpContactByPhoneNumber(SignUpUser signUpUser) {

		SignUpUser signUpContactList = (SignUpUser) hibernateTemplate
				.find(DariAstroQueries.SIGNUP_QUERY_BY_PHONE_NUMBER, new Object[] { signUpUser.getPhoneNumber() })
				.get(0);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return signUpContactList;
	}

	public SignUpUser getSignUpContactByEmailIdAndPhoneNumber(LoginUser loginUser) {

		SignUpUser signUpUserList = (SignUpUser) hibernateTemplate
				.find(DariAstroQueries.SIGNUP_QUERY_BY_EMAILID_OR_PHONENUMBER,
						new Object[] { loginUser.getEmailId(), loginUser.getPhoneNumber() })
				.get(0);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return signUpUserList;
	}

	public List<LoginUserDetails> getCheckForFirstLogin(String phoneNumber, String emailId) {

		@SuppressWarnings("unchecked")
		List<LoginUserDetails> loginUserDetailsList = (List<LoginUserDetails>) hibernateTemplate
				.find(DariAstroQueries.LOGIN_QUERY_BY_EMAILID_OR_PHONENUMBER, new Object[] { emailId, phoneNumber });

		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return loginUserDetailsList;
	}

	public SignUpUser getSignUpContactByEmailIdAndPhoneNumber(LogoutUser logoutUser) {

		SignUpUser signUpUserList = (SignUpUser) hibernateTemplate
				.find(DariAstroQueries.SIGNUP_QUERY_BY_EMAILID_OR_PHONENUMBER,
						new Object[] { logoutUser.getUserEmailId(), logoutUser.getUserPhoneNumber() })
				.get(0);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return signUpUserList;
	}

	public SignUpUser getSignUpContactByEmailIdAndPhoneNumberForUpdatePassword(UpdatePassword updatePassword) {

		SignUpUser signUpUsertList = (SignUpUser) hibernateTemplate
				.find(DariAstroQueries.SIGNUP_QUERY_BY_EMAILID_OR_PHONENUMBER,
						new Object[] { updatePassword.getEmailId(), updatePassword.getPhoneNumber() })
				.get(0);
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		return signUpUsertList;
	}
}
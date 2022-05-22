package com.example.demo.auth;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    AccountRepository accountRepository;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
//        List<ApplicationUser> applicationUsers = Lists.newArrayList(
//                new ApplicationUser(
//                        "annasmith",
//                        passwordEncoder.encode("password"),
//                        STUDENT.getGrantedAuthorities(),
//                        true,
//                        true,
//                        true,
//                        true
//                ),
//                new ApplicationUser(
//                        "linda",
//                        passwordEncoder.encode("password"),
//                        ADMIN.getGrantedAuthorities(),
//                        true,
//                        true,
//                        true,
//                        true
//                ),
//                new ApplicationUser(
//                        "tom",
//                        passwordEncoder.encode("password"),
//                        ADMINTRAINEE.getGrantedAuthorities(),
//                        true,
//                        true,
//                        true,
//                        true
//                )
//        );
		List<ApplicationUser> applicationUsers = new ArrayList<ApplicationUser>();
//		applicationUsers.add(new ApplicationUser("root", passwordEncoder.encode("root"), ADMIN.getGrantedAuthorities(), true, true, true, true));
		List<Account> allAccounts = accountRepository.findAll();
		for(int i=0;i<allAccounts.size();i++)
		{
			Account acc = allAccounts.get(i);
			ApplicationUser applicationUser = new ApplicationUser(acc.getUsername(), passwordEncoder.encode(acc.getPassword()),ApplicationUserRole.getValueOf(acc.getTypeOfAccount()).getGrantedAuthorities(),
					true, true, true, true );
			applicationUsers.add(applicationUser);
		}
		return applicationUsers;
    }

}

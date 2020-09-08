package me.dave;

import me.dave.account.Account;
import me.dave.account.AccountRepository;
import me.dave.account.Role;
import me.dave.account.RoleRepository;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.SessionFactory를 통해 neo4j를 사용.
        Account account1 = new Account();
        account1.setUsername("Dave");
        account1.setEmail("dave@gmail.com");

        Role role1 = new Role();
        role1.setRoleName("admin");
        account1.getRoles().add(role1);

        Session session = sessionFactory.openSession();
        session.save(account1);
        sessionFactory.close();

        //2.Repository를 통해 neo4j를 사용.
        Account account2 = new Account();
        account2.setUsername("Camel");
        account2.setEmail("camel@naver.com");

        Role role2 = new Role();
        role2.setRoleName("user");
        account2.getRoles().add(role2);

        accountRepository.save(account2);

        System.out.println("Process was finished");
    }
}

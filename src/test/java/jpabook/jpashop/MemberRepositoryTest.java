package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember(){
        Member member = new Member();
        member.setUsername("memberA");

        Long id = memberRepository.save(member);
        Member member1 = memberRepository.find(id);

        Assertions.assertThat(id).isEqualTo(member1.getId());
        Assertions.assertThat(member.getUsername()).isEqualTo(member1.getUsername());

    }
}
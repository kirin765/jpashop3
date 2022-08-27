package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepositoryOld memberRepositoryOld;

    @Autowired
    EntityManager em;


    @Test
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        em.flush();

        Assertions.assertThat(member).isEqualTo(memberRepositoryOld.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {

        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

        Assertions.fail("예외가 발생해야 한다.");
    }
}
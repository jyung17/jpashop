package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
  
  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  EntityManager em;
  
  @Test
//  @Rollback(false)
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("joo");
    
    //when
    Long saveId = memberService.join(member);
  
    //then
    assertEquals(member, memberService.findOne(saveId));
  }
  
  @Test()
  public void 중복_회원_예외() throws Exception {
    //given
    Member member1 = new Member();
    member1.setName("joo");
    
    Member member2 = new Member();
    member2.setName("joo");
    
    //when
    memberService.join(member1);
    
    //then
    assertThrows(IllegalStateException.class, () -> {
      memberService.join(member2);//예외가 발생해야 한다!!!
    });
  }
}
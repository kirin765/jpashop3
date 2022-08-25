 ##연관 관계 메소드
 ```java
Member member = new Member();
Order order = new Order();

member.getOrders().add(order);
order.setMember(member);

```
- 서로 @OneToOne등 연관관계인 경우 번거롭게 둘다 set해주지않고
- 하나의 메소드로 양쪽 다 set

```java
public void setMember(Member member) {
    member.getOrders().add(this);
    this.member = member;
}
```

##@AllArgsConstructor
```java
@AllArgsConstructor
public class MemberService{
    private MemberRepository memberRepository;
    
    // 생성자 자동생성 및 자동주입
    // public MemberService(MemberRepository memberRepository) {
    // this.memberRepository = memberRepository;
    // }
}
```

##@RequiredArgsConstructor
```java
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;
    
    // final 있는 경우만 자동주입
    // public MemberService(MemberRepository memberRepository) {
    // this.memberRepository = memberRepository;
    // }
}
```

##트랜잭션 스크립트 모델, 도메인 모델
- 트랜잭션 스크립트는 서비스계층에서 비즈니스 로직을 작성
- 도메인 모델은 핵심 비즈니스 로직을 엔티티계층에서 메소드에 작성

##Entity 대신 DTO
- Entity에 @NotEmpty같은 스펙이 있을때 경우에 따라 다른 경우 있을 수 있다.
- DTO를 통해 API 스펙에 영향을 주지않게 관계를 단절
- 상세 API 스펙에 따른 설정은 DTO에 Entity는 좀 더 일반적으로 사용가능
```java
//MemberApiController.java
@PostMapping("/api/v1/members")
public CreateMemberResponse saveMemberV1(
        @RequestBody 
        @Valid 
        Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
}

@PostMapping("/api/v2/members")
public CreateMemberResponse saveMemberV2(
        @RequestBody 
        @Valid 
        CreateMemberRequest request){
    Member member = new Member();
    member.setName(request.getName());

    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
}
```
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
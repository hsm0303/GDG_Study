WIL
---
이번 시간에는 부품이 주인공을 건들지 못하도록 하는 법을 배웠다.
전까지는 infrastructure가 변하면 application도 바뀌는 구조를 가졌었다. application이 infrastructure를 붙들고 있었기 떄문이다.
여기서 SOLID의 D가 나온다는 것을 알았다. 즉, 세부사항이 추상화에 의존해야 한다는 것을 알았다.
domain은 interface를 만들어서 port가 되고 infrastructure은 외부기술을 건드리기 때문에 adapter라는 것을 알게 되었다.
이렇게 Adapter를 사용하여 ProductRepositoryAdapter를 만드는 시간을 가졌다. ProductRepositoryAdapter는 ProductJpaRepository가 가지고 있는 외부 기술을 사용하여 ProductRepository를 구현하는 역할을 한다는 것을 알았다.
이렇게 하여 application과 infrastructure을 완전히 단절시키는 실습을 완료했다. 즉, Productservice가 ProductRepository를 부품처럼 쓰게 되었다는 것이다.
Optional을 사용하는 이유는 NullPointerException을 방지하고 값이 없을 수도 있다는 것을 메서드 반환 타입으로 표현하기 위해서임을 찾아보고 알게 되었다.

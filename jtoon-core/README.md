# JToon Core

Jtoon 서비스의 해결하고자하는 도메인에 대한 모듈

## 계층별 지키고자 하는 조건
(layered 기반)

- Presentation Layer : 외부 의존성이 높은 영역, Controller, dto 존재
- Domain Layer(Service) : 해결하고자 하는 문제인 도메인에 집중하고자 하는 layer
- Repository Layer : 상세 구현 로직이 다양한 자원에 접근할 수 있는 기능을 제공하는 레이어

## Include

### 초기
- `core-api`: 전체 도메인 로직에 대한 모듈만 존재

단점: 
1. 모든 기능이 해당 모듈에 몰려있어서 많은 의존성을 가지고 있다.
2. 도메인은 문제를 해결하기 위한 영역이기 때문에 외부의 의존성을 가지는 것이 맞을까?
3. Batch서버를 따로 올린다고 했을 때, Runnable한 API가 2개가 생기게 되는데 이때 동일한 도메인 기능을 서로 다르게 관리하게 된다.

## 2번째 리팩토링

### Core API
Presentation 영역, 외부 의존성이 높다.

- Controller, Dto가 존재
- 확장되지 않는 서비스에 대한 로직 예를들어 Admin은 여기에 추가하고 추후에 변경

### Core domain
오직 Domain 서비스만 관리하는 모듈

- Domain서 서비스를 문제만 해결하기 위해 spring framework의 의존성을 없애는 것으로 한다.
- spring framework가 자체적으로 변경되든, framework를 변경할때 domain 로직이 변경되는 것이 옳지 않다고 생각
  - 단 DB가 존재하기 때문에, JPA는 추가

#### 문제
- Bean등록을 어떻게 하면 좋을까?
- Transaction은 어떻게 해야할까


이렇게하면, 여러 개의 Runnable한 서비스가 가능하다

단점: 
- 도메인이 DB에 의존적이다. 근데 DB를 변경하는 경우가 많을까? 에 대한 생각을 해볼 필요가 있다.
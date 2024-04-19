# JToon Core

Jtoon 서비스의 해결하고자하는 도메인과 사용자 요청에 대한 대한 모듈
- core-api
- core-domain


## 계층별 지키고자 하는 조건
- Presentation Layer : 외부 의존성이 높은 영역, Controller, dto 존재
- Domain Layer(Service) : 해결하고자 하는 문제인 도메인에 집중하고자 하는 layer
- Repository Layer : 상세 구현 로직이 다양한 자원에 접근할 수 있는 기능을 제공하는 레이어


# Include

---

## 1.초기
- `core-api`: 전체 도메인 로직에 대한 모듈만 존재

단점: 
1. 모든 기능이 해당 모듈에 몰려있어서 많은 의존성을 가지고 있다.
2. 도메인은 문제를 해결하기 위한 영역이기 때문에 외부의 의존성을 가지는 것이 맞을까?
3. Batch서버를 따로 올린다고 했을 때, Runnable한 API가 2개가 생기게 되는데 이때 동일한 도메인 기능을 서로 다르게 관리하게 된다.

---

## 2. 2번째 리팩토링

### Core API
Presentation 영역, 외부 의존성이 높다.

- Controller, Dto가 존재
- 확장되지 않는 서비스에 대한 로직 예를들어 Admin은 여기에 추가하고 추후에 변경

### Core domain
오직 Domain 서비스만 관리하는 모듈

- Domain서 서비스를 문제만 해결하기 위해 spring framework의 의존성을 없애는 것으로 한다.
- spring framework가 자체적으로 변경되든, framework를 변경할때 domain 로직이 변경되는 것이 옳지 않다고 생각 
- 다만 RDB 모듈 분리에 따른 트레이드오프를 생각하여 현재는 결합상태

#### 문제
- Bean등록을 어떻게 하면 좋을까?
- Transaction은 어떻게 해야할까


이렇게하면, 여러 개의 Runnable한 서비스가 가능하다

단점: 
- 도메인이 DB에 의존적이다. 근데 DB를 변경하는 경우가 많을까? 에 대한 생각을 해볼 필요가 있다.

따라서 현재는 글로벌 캐시 및 event기반 pub/sub용 redis은 db모듈로 따로 뺐지만 RDB의 경우 domain과 결합되어 있다.
rdb를 도메인과 분리하여 외부 의존성을 전부 빼고, 순수 도메인에 집중하게 된다면 Transaction 관리에 대한 문제를 생각해봐야 할 것 같다.

# JToon Core

Jtoon 서비스의 해결하고자하는 도메인에 대한 모듈

## 계층별 지키고자 하는 조건
(layered 기반)

- Presentation Layer : 외부 의존성이 높은 영역, Controller, dto 존재
- Domain Layer(Service) : 해결하고자 하는 문제인 도메인에 집중하고자 하는 layer
- Repository Layer : 상세 구현 로직이 다양한 자원에 접근할 수 있는 기능을 제공하는 레이어

## Include
- `core-api`: 전체 도메인 로직에 대한 모듈만 존재
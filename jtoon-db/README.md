# Jtoon-db
- db의 의존성을 domain모듈에서 완전히 배제하고
- 도메인 모듈이 여러 도메인 모듈로 분리가 됐을 경우 공통된 config를 추가적으로 작성해주는 대신 모듈을 분리하여 사용할 수 있다는 장점으로 사용

## Include
현재는  Redis만 존재한다.

- `core-api` -runtimeOnly-> `jtoon-db`
- `core-domain` <-compileOnly- `jtoon-db`

core-api는 jtoon-db를 runtime시 classpath를 찾으며 실행시 관련 정보를 가져올 수 있게 하였다.
도한, `jtoon-db`가 `core-domain`을 컴파일때 가지게 하면서 db에서 domain에 관련된 정보를 사용할 수 있게 하였다.
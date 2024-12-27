
### 프로그램 실행 전 환경
- application.yml 파일 수정
  - url, username, password를 수정 필요
- mysql 스크립트 실행 ( 프로그램 실행전 테이블 생성)
  - schema.sql 파일 참고
- 배치에 이용되는 파일은 resources/fulldata.csv 파일로 존재

### 프로그램 실행
- java -jar spring-batch-0.0.1-SNAPSHOT-plain.jar --job.name=restaurantDataJob
- 또는 인텔리제이에서 program arguments에  --job.name=restaurantDataJob추가 후 실행

### 테스트 전 환경
- docker 설치 필요

### 테스트 확인 방법
- gradle test 또는 개별 테스트 수행(개별부분만 확인 가능)

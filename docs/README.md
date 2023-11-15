# 미션 - 크리스마스 프로모션

## 혜택 목록

- 크리스마스 디데이 할인(1000+100..): 총 주문금액에 적용. 기간은 2023.12.1-2023.12.25
- 평일 할인(일요일~목요일): 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일,토요일): 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개 증정

- 이벤트 기간: 크리스마스 디데이 할인을 제외한 다른 이벤트는 2023.12.1-2023.12.31

## 진행과정

- 인사말 출력하기
- 예상 방문 날짜 입력받기
- 예상 방문 날짜 검증 및 저장하기
    - [x] 예외 : 1이상 31이하의 숫자가 아닌 경우
- 주문 메뉴와 개수 입력받기(e.g. 해산물 파스타-2,레드와인-1,초코케이크-1)
    - [x] 예외 : 메뉴의 개수가 1이상이 아닌 경우
    - [x] 예외 : 없는 메뉴를 주문한 경우
    - [x] 예외 : 메뉴 형식이 예시와 다른 경우 -> 따로 검증이 필요한가? 나중에 확인 필요**
    - [x] 예외 : 중복 메뉴를 입력한 경우
    - [x] 예외 : 메뉴 개수의 총 합이 20을 초과한 경우
    - [x] 예외 : 음료만 주문한 경우
    - [x] 예외 : 총 금액이 10000원 미만일 경우 모든 이벤트가 적용되지 않는다.
- 혜택 미리보기 문구 출력하기
- 주문 메뉴 출력하기 (순서는 자유)
- 할인 전 총주문 금액 보여주기(숫자에 "," 넣어주기)
- 증정 메뉴 보여주기(없는 경우 "없음" 출력)
- 혜택 내역 보여주기(고객에게 적용된 이벤트만, 순서는 자유, 없는 경우 "없음")
- 총혜택 금액 보여주기(할인 금액의 합계 + 증정 메뉴의 가격)
- 할인 후 예상 결제 금액 보여주기(할인 전 총주문 금액 - 할인 금액)
- 이벤트 배지 보여주기(없는 경우 "없음")
- [x] 주의 : 각 출력마다 공백 넣어주기
- [x] 주의 : 각 금액마다 "," 넣어주기
- [x] 주의 : 할인된 금액은 음수로 출력하기

## 기능목록

### view

- Input : 입력 받는 기능
    - 예상 방문 날짜 입력받기
    - 주문 메뉴와 개수 입력받기
- Output : 각종 출력기능
    - 인사말 출력하기
    - 혜택 미리보기 문구 출력하기
    - 각종 혜택 및 금액 출력하기
        - 금액 출력 시 천 단위마다 "," 붙여주기
        - 필요시 공백 넣어주기

---

### user

- VisitDate : 예상 방문 날짜 검증 및 저장 기능
- UserMenu : 주문 메뉴와 개수를 이용한 기능
    - MenuNames : 주문 메뉴를 검증 및 저장
    - MenuCounts : 주문 메뉴 개수를 검증 및 저장
- TotalPrice : 할인 전 후 총 금액을 계산하고 저장하는 기능
- UserInformation : 각종 이벤트들을 처리하는데 필요한 주문 정보를 모아두는 기능

---

### benefit

- EventPrice : 혜택 금액을 계산하고 저장하는 기능
- EventManager : 각종 이벤트들을 모아 EventPrice를 산출하는 기능
- Badge : 배지 저장 기능(총 혜택 금액에 따라 다른 배지 저장)

---

### event

- Event : 이벤트 interface
- GiveEvent : '할인'이 아닌 '증정' 종류의 이벤트 interface
- DDayEvent : 크리스마스 디데이 할인
- WeekdayEvent : 평일 할인(일요일 ~ 목요일)
- WeekendEvent : 주말 할인(금요일, 토요일)
- SpecialEvent : 특별 할인
- GiveAwayEvent : 증정 이벤트

---

### option

- Menu : 메뉴판 enum
- Error : 에러메시지 enum
- EventName : 이벤트명 및 해당 이벤트 인스턴스를 가진 enum
- EventOption : 이벤트 옵션 관련 상수 클래스

---

### util

- Converter : 각종 변환 기능

## 테스트 목록

### controller

- [x] ControllerTest

---

### domain

#### Event

- [x] DDayEventTest
- [x] GiveAwayEventTest
- [x] SpecialEventTest
- [x] WeekdayEventTest
- [x] WeekendEventTest

#### user

- [x] TotalPriceTest
- [x] UserMenuTest
- [x] VisitDateTest
- [x] MenuNamesTest
- [x] MenuCountsTest

#### benefit

- [x] BadgeTest
- [x] EventManagerTest
- [x] EventPriceTest

---

### option

- [x] EventNameTest
- [x] MenuTest

---

### util

- [x] ConverterTest

---

### view

- [x] OutputTest
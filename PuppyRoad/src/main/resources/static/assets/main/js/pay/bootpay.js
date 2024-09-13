/**
 * 앞에 필수 코드
  <script src="https://js.bootpay.co.kr/bootpay-5.0.1.min.js" type="application/javascript"></script>
 */

//부트페이 결제 요청 후 결과 처리
async function sendPay() {
    try {
        const response = await Bootpay.requestPayment(payForm());
        switch (response.event) {
            case 'issued':
                // 가상계좌 입금 완료 처리
                break
            case 'done':
                console.log(response);
                // 결제 완료 처리
                break
            case 'confirm': //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                console.log(response.receipt_id);
                /**
                 * 1. 클라이언트 승인을 하고자 할때
                 * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                 */
                const confirmedData = await Bootpay.confirm(); //결제를 승인한다
                if (confirmedData.event === 'done') {
                    //결제 성공
                }

                /**
                 * 2. 서버 승인을 하고자 할때
                 * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                 * Bootpay.destroy(); //결제창을 닫는다.
                 */
                break
        }
    } catch (e) {
        // 결제 진행중 오류 발생
        // e.error_code - 부트페이 오류 코드
        // e.pg_error_code - PG 오류 코드
        // e.message - 오류 내용
        console.log(e.message)
        switch (e.event) {
            case 'cancel':
                // 사용자가 결제창을 닫을때 호출
                console.log(e.message);
                break
            case 'error':
                // 결제 승인 중 오류 발생시 호출
                console.log(e.error_code);
                break
        }
    }
}


//부트페이 결제 폼(통합결제)
function payForm(price = 100, times = 30, user = { userId: 'admin', name: "관리자", phone: "01011112222", email: "alscjf2738@naver.com" }) {
    getEndDate();
    const data = {
        "application_id": "59a4d323396fa607cbe75de4",
        "price": price,
        "order_name": "도그워커 비용",
        "order_id": "0001",
        //"pg": "다날",
        //"method": "카드",
        "tax_free": 0,
        "user": {
            "id": user.userId,
            "username": user.name,
            "phone": user.phone,
            "email": user.email
        },
        "items": [
            {
                "id": "item_id",
                "name": "산책 '" + times + "'분",
                "qty": 1,
                "price": price
            }
        ],
        "extra": {
            "open_type": "iframe",
            "card_quota": "0,2,3",
            "escrow": false,
            "deposit_expiration": getEndDate(),
            "test_deposit": true,    //가상계좌 모의입금
            "show_close_button": true
        }
    }

    return data;
}

function getEndDate() {
    let today = new Date();
    today.setMinutes(today.getMinutes() + 30);
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let date = today.getDate();
    let hours = today.getHours();
    let minutes = today.getMinutes();
    let seconds = today.getSeconds();

    let endDate = `${year}-
                    ${month > 10 ? month : '0' + month}-
                    ${date > 10 ? date : '0' + date} 
                    ${hours > 10 ? hours : '0' + hours}:
                    ${minutes > 10 ? minutes : '0' + minutes}:
                    ${seconds > 10 ? seconds : '0' + seconds}`;
    console.log(endDate);

    return endDate;
}
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complete Reservation</title>
</head>
<body>
<h2>Complete Reservation</h2>
    Airline: ${flight.operatingAirlines}<br/>
    Depature City: ${flight.departureCity}<br/>
    Arrival City: ${flight.arrivalCity}<br/>

<form action="completeReservation" method="POST">
    <pre>
    <h2>Passenger Details: </h2>
    First Name: <input type="text" name="passengerFirstName"/>
    Last Name: <input type="text" name="passengerLastName"/>
    Email: <input type="text" name="passengerEmail"/>
    Phone: <input type="text" name="passengerPhone"/>

<form >
    <script src="https://remitademo.net/payment/v1/remita-pay-inline.bundle.js"></script>

    <button type="button" onclick="makePayment()"> Pay With Remita </button>
</form>

    <h2>Card Details: </h2>
    Name on Card: <input type="text" name="nameOnTheCard"/>
    Card No: <input type="text" name="cardNumber"/>
    Expiry Date: <input type="text" name="expirationDate"/>
    CVV: <input type="text" name="securityCode"/>

    <input type="hidden" name="flightId" value="${flight.id}">
    <input type="submit" value="confirm">
    </pre>
</form>
</body>
</html>
<script>
    function makePayment() {
        var paymentEngine = RmPaymentEngine.init({
            key:'b21vbml5aTI0QGdtYWlsLmNvbXw0MTQwNzI3OHxhYWE2MWJkOWQzOGIxNGNmM2UwMjUxNzEyN2IyYzA3YTYzM2U2ZmNiYjMzOTdhZDY1ODY0MTY5M2ZhNDg4MDkzZjIyMDM0ZDYwODdiZjE0NjYxMWMyZGY2NjMwYzc5MjkyNDY4NTU1ODMyZDU5MjhiNzA1YWYzOThmZWZmMGI3ZA==',
			customerId: "ilesanmi@systemspecs.com.ng",
            firstName: "Lisa",
            lastName: "Spark",
			narration: "bill pay + 113353243334",
            email: "demo@remita.net",
            amount: 5000,
            onSuccess: function (response) {
                console.log('callback Successful Response', response);
            },
            onError: function (response) {
                console.log('callback Error Response', response);
            },
            onClose: function () {
                console.log("closed");
            }
        });

        paymentEngine.showPaymentWidget();
    }
</script>

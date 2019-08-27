package ru.los;

import calculator.wsdl.Add;
import calculator.wsdl.AddResponse;
import calculator.wsdl.Divide;
import calculator.wsdl.DivideResponse;
import calculator.wsdl.Multiply;
import calculator.wsdl.MultiplyResponse;
import calculator.wsdl.Subtract;
import calculator.wsdl.SubtractResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/*Необходимо разработать REST сервис-адаптер к SOAP веб-сервису.
Сервис источник: http://www.dneonline.com/calculator.asmx
Выходной частью сервиса-адаптера должен быть REST-интерфейс, который принимает значения для передачи в SOAP веб-сервис.
Необходимо предусмотреть валидацию запросов к REST-сервису на предмет их наличия, корректности формата и т.п.*/


public class SoapSource extends WebServiceGatewaySupport {

    /**
     * sum
     */

    public AddResponse add(int intA, int intB) {
        Add request = new Add();
        request.setIntA(intA);
        request.setIntB(intB);

        return (AddResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://www.dneonline.com/calculator.asmx", request, new SoapActionCallback("http://tempuri.org/Add"));
    }

    /**
     * substract
     */
    public SubtractResponse subtract(int intA, int intB) {
        Subtract request = new Subtract();
        request.setIntA(intA);
        request.setIntB(intB);

        return (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://www.dneonline.com/calculator.asmx", request, new SoapActionCallback("http://tempuri.org/Subtract"));
    }

    /**
     * multipliction
     */
    public MultiplyResponse multiply(int intA, int intB) {
        Multiply request = new Multiply();
        request.setIntA(intA);
        request.setIntB(intB);

        return (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://www.dneonline.com/calculator.asmx", request, new SoapActionCallback("http://tempuri.org/Multiply"));
    }

    /**
     * division
     */
    public DivideResponse divide(int intA, int intB) {
        Divide request = new Divide();
        request.setIntA(intA);
        request.setIntB(intB);

        return (DivideResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://www.dneonline.com/calculator.asmx", request, new SoapActionCallback("http://tempuri.org/Divide"));
    }

}
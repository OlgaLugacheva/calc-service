package ru.los.data.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.*;
import ru.los.SoapSource;
import ru.los.exception.ValidateException;


@RestController
@CrossOrigin
@Api(value = "calculator", description = "Калькулятор")
public class CalcController {

    private static final Logger LOGGER = Logger.getLogger(CalcController.class);

    private String a;
    private String b;

    @Autowired
    public CalcController() {
    }

    @ApiOperation(value = "Суммирование")
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String getSum(@RequestParam(value = "a") String a,
                         @RequestParam(value = "b") String b) {
        SoapSource client = getSoapSource();
        try {
            validateNumbers(a, b, false);
        } catch (ValidateException e) {
            return e.getMessage();
        }
        return String.valueOf(client.add(Integer.parseInt(a), Integer.parseInt(b)).getAddResult());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/substract")
    public String getSubstract(@RequestParam(value = "a") String a,
                               @RequestParam(value = "b") String b) {
        SoapSource client = getSoapSource();
        return String.valueOf(client.subtract(Integer.parseInt(a), Integer.parseInt(b)).getSubtractResult());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/divide")
    public String getDivision(@RequestParam(value = "a") String a,
                              @RequestParam(value = "b") String b) {
        SoapSource client = getSoapSource();
        try {
            validateNumbers(a, b, true);
        } catch (ValidateException e) {
            return e.getMessage();
        }
        return String.valueOf(client.divide(Integer.parseInt(a), Integer.parseInt(b)).getDivideResult());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multiple")
    public String getMultiplication(@RequestParam(value = "a") String a,
                                    @RequestParam(value = "b") String b) {
        SoapSource client = getSoapSource();
        return String.valueOf(client.multiply(Integer.parseInt(a), Integer.parseInt(b)).getMultiplyResult());
    }

    private SoapSource getSoapSource() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("calculator.wsdl");
        SoapSource client = new SoapSource();
        client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


    private void validateNumbers(String a, String b, boolean divFlag) throws ValidateException {

        Integer a1;
        int b1;
        try {
            if (a == null || b == null) throw new ValidateException(" The parameter's value was not set ");

            a1 = Integer.parseInt(a);
            b1 = Integer.parseInt(b);
            if (divFlag && b1 == 0) throw new ValidateException(" Сan not be divided by zero");
        } catch (NumberFormatException e) {
            throw new ValidateException(e.toString());
        }

    }

}
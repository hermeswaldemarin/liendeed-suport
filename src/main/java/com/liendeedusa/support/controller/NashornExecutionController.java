package com.liendeedusa.support.controller;

import com.liendeedusa.support.to.NashornExecRequest;
import com.liendeedusa.support.to.NashornExecReturn;
import jdk.nashorn.api.scripting.JSObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.*;

/**
 * Created by hermeswaldemarin on 26/05/17.
 */
@Controller
public class NashornExecutionController {

    private static ChromeDriver driver = null;

    public static ChromeDriver getChromeDriver(){
        if(driver == null){
            driver = new ChromeDriver();
        }

        return driver;
    }

    @PostMapping("/nashorn/execute")
    public ResponseEntity<Object> getJSON(@RequestParam("script") String script) {
        NashornExecReturn ret = new NashornExecReturn();
        Object js = null;
        try{
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            Compilable compilable = (Compilable) engine;
            Invocable invocable = (Invocable) engine;

            final CompiledScript compiled = compilable.compile(script);

            compiled.eval();

            js = invocable.invokeFunction("execute", getChromeDriver());

            //ret.setObjectReturn(js.);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(js, HttpStatus.OK);

    }


    @PostMapping("/nashorn/executejson")
    public ResponseEntity<Object> execCodeJson(@RequestBody NashornExecRequest request) {
        NashornExecReturn ret = new NashornExecReturn();
        Object js = null;
        try{
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            Compilable compilable = (Compilable) engine;
            Invocable invocable = (Invocable) engine;

            final CompiledScript compiled = compilable.compile(request.getScript());

            compiled.eval();

            js = invocable.invokeFunction("execute", getChromeDriver());

            //ret.setObjectReturn(js.);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(js, HttpStatus.OK);

    }

}

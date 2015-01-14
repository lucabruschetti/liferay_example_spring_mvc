package it.accenture.prova.controllers;

import it.accenture.prova.facade.GestioneUtenteProvaFacade;
import it.accenture.prova.model.UtenteProva;
import it.accenture.prova.utils.D3PieConverter;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Controller(value = "ProvaController")
@RequestMapping("VIEW")
public class ProvaController {

	@Autowired
	private GestioneUtenteProvaFacade facade;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,RenderResponse response,Model model){
		return "defaultRender";
	}

	@ResourceMapping(value =  "utenteResource")	
	public View drawAgeDIstributionPie(@RequestParam("pattern") String pattern){
		
		List<UtenteProva> allUtenti=facade.findAllUtenti();
		List <D3PieConverter> converter = new ArrayList<D3PieConverter>();
		int countLessThanFifty=0;
		int countMoreEqualsFifty=0;

		for (UtenteProva utente:allUtenti){
			if (utente.getAge().intValue()>=Integer.valueOf(pattern).intValue()){
				countMoreEqualsFifty++;
			}else{
				countLessThanFifty++;
			}
		}

		MappingJacksonJsonView view = new MappingJacksonJsonView();
		
		D3PieConverter conv1=new D3PieConverter();
		conv1.setId("1");
		conv1.setLabel("Age less than "+Integer.valueOf(pattern).intValue());
		conv1.setValue(countLessThanFifty);
		conv1.setColor("#0c6197");
		
		
		D3PieConverter conv2=new D3PieConverter();
		conv2.setId("2");
		conv2.setLabel("Age more or equals than "+Integer.valueOf(pattern).intValue());
		conv2.setValue(countMoreEqualsFifty);
		conv2.setColor("#90c469");
		
		converter.add(conv1);
		converter.add(conv2);
		
		view.addStaticAttribute("utenti", converter);
		return view;
	}

}

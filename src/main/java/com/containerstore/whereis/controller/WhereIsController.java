package com.containerstore.whereis.controller;

import com.containerstore.common.util.Format;
import com.containerstore.whereis.service.ConferenceRooms;
import com.containerstore.whereis.service.ConferenceRoomsImpl;
import com.containerstore.whereis.viewmodel.WhereIsListViewModel;
import com.containerstore.whereis.viewmodel.WhereIsViewModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Controller
public class WhereIsController {
    @GetMapping("/")
    public String whereisForm(Model model) {
        model.addAttribute("queryModel", new WhereIsViewModel());
        return "whereis";
    }
    @GetMapping("/conference_rooms")
    public String whereisList(Model model,
    	@ModelAttribute WhereIsListViewModel listModel) {       
        ConferenceRooms conferenceRooms = new ConferenceRoomsImpl();
        listModel.setRoomsLocation(conferenceRooms.getRoomLocationList());
        model.addAttribute("listModel", listModel);
        return "WhereisList";
    }
    
    @PostMapping("/")
    public String submitForm(
            Model model,
            @ModelAttribute WhereIsViewModel viewModel) {
        //viewModel.setResult(replyFor(viewModel.getQuery()));    	
    	viewModel.setResult(processRequest(viewModel.getQuery()));
        model.addAttribute("queryModel", viewModel);
        return "whereis";
    }

    @PostMapping("/incoming")
    @ResponseStatus(OK)
    public void incoming(
            @RequestBody String query,
            @RequestParam("From") String origin) {

        Twilio.init("(account sid)", "(auth token)");

        MessageCreator creator = Message.creator(
                new PhoneNumber(origin),
                new PhoneNumber("4694164526"),
                replyFor(query));
        creator.create();
    }

    protected String[] processRequest(String query) {
		if((query == null)||(query.trim()).equals("")){
			return new String[] {"Empty room name. Please enter room name."};
		}
		query = (query.trim()).toLowerCase();
		String [] result;
		ConferenceRooms conferenceRooms = new ConferenceRoomsImpl();
		if (query.equals(ConferenceRooms.roomsListReq)){
			result = conferenceRooms.getRoomsList();
			for(int i=0; i<result.length;i++){
				result[i] = (Format.titleCase(result[i]));
			}

		} else {
			String location = conferenceRooms.locationOf(query);
			if((location == null)||(location.equals(""))){
				location = "somewhere, but I don't know where"; 
			} else {
                location =  String.format("%s is located : %s", query, location);
			}
			result = new String[] {location};
    	}
		return result;
    }

    private String replyFor(String query) {
        String location;

        switch (query.trim().toLowerCase()) {
            case "fill their baskets":
            case "service selection price":
            case "man in the desert":
            case "air of excitement":
                location = "in the vendor conference area (off of reception)";
                break;
            case "data central":
                location = "in the Information Systems area";
                break;
            case "perfect product presentation":
            case "main and main":
                location = "at the north end of the Information Systems area";
                break;
            case "1 great = 3 good":
            case "intuition does not come to an unprepared mind":
                location = "off the atrium, behind reception";
                break;
            case "gumby":
                location = "where gumby has always been located...c'mon!";
                break;
            case "contain yourself":
                location = "upstairs, south end, adjacent to CSD";
                break;
            case "we love our employees":
                location = "upstairs, south end, adjacent to CSD (seating area in front of Contain Yourself)";
                break;
            case "all eyes on elfa":
                location = "upstairs, southwest corner";
                break;
            case "service = selling":
            case "fun and functional":
                location = "upstairs, southwest corner, adjacent to loss prevention";
                break;
            case "communication is leadership":
                location = "upstairs, northwest corner, adjacent to the executive suite";
                break;
            case "we sell the hard stuff":
            case "blue sky":
                location = "up the stairs, turn right (adjacent to merchandising)";
                break;
            default:
                location = "somewhere, but I don't know where";
                break;
        }
        
        return String.format("%s is located %s", query, location);
    }
}

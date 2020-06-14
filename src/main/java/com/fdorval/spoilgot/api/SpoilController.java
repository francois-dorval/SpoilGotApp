package com.fdorval.spoilgot.api;

import com.fdorval.spoilgot.api.model.GotCharacterFront;
import com.fdorval.spoilgot.business.SpoilBusiness;
import com.fdorval.spoilgot.dao.model.Season;
import com.fdorval.spoilgot.util.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * le point d'entrée de l'API...
 *
 * @author franc
 */
@RestController
public class SpoilController {

    @Autowired
    SpoilBusiness spoilBusiness;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public List<GotCharacterFront> getCharacter(@RequestParam(value = "season", defaultValue = "1") String season) throws com.fdorval.spoilgot.util.exception.TechnicalException, FunctionalException {
        return spoilBusiness.getCharactersInSeason(Season.fromString(season));
    }


}

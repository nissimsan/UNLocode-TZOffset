package io.tradelens.timezone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tradelens.timezone.mapper.TimezoneMapper;

@RestController
public class ConverterController {

  @Autowired
  UNLocodeService unLocodeService;
	
	@RequestMapping("/locodeToTimezone/{unLocode}")
	public String getUNLocodeCoordinate(@PathVariable String unLocode) {
		
		// Get the unlocode-style old fashioned coordinate from the DB
		String uglyCoordinate = unLocodeService.getUNLocode(unLocode).getUnCoordinates();

		// Convert the old fashioned coordinates to normal lat/long
		String uglyLat = uglyCoordinate.split(" ")[0];
		String uglyLong = uglyCoordinate.split(" ")[1];
		
		// Find the timezone from the TimezoneMapper class
		String timeZone = TimezoneMapper.latLngToTimezoneString(latConverter(uglyLat), longConverter(uglyLong));
//		System.out.println(timeZone);
		
		// Convert the timezone to offset
		LocalDateTime now = LocalDateTime.now();
		ZoneId zone = ZoneId.of(timeZone);
		return zone.getRules().getOffset(now).toString();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/storeLocodes")
	public void addBottles(@RequestBody List<UNLocode> unLocodes) {
		for (UNLocode unLocode : unLocodes) {
			unLocodeService.saveOrUpdate(unLocode);						
		}
	}

	public static double latConverter(String crapLat) {
		String noNS = crapLat.substring(0, crapLat.length()-1);
		String nS = crapLat.substring(crapLat.length()-1, crapLat.length());
		Double priLat = Double.parseDouble(noNS);
		priLat = priLat/100d;		
		Double lat = (priLat%1)/0.6d + (priLat-priLat%1);
		if (nS.equals("S"))
			lat = lat*-1;
		return lat;
	}
	
	public static double longConverter(String crapLon) {
		String noEW = crapLon.substring(0, crapLon.length()-1);
		String eW = crapLon.substring(crapLon.length()-1, crapLon.length());
		Double priLon = Double.parseDouble(noEW);
		priLon = priLon/100d;		
		Double lon = (priLon%1)/0.6d + (priLon-priLon%1);
		if (eW.equals("W"))
			lon = lon*-1;
		return lon;
	}
	
}
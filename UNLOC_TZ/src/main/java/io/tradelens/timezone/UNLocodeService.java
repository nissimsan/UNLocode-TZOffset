package io.tradelens.timezone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UNLocodeService {

	@Autowired
	private UNLocodeRepository unLocodes;

	public UNLocode getUNLocode(String unLocode) {
		return unLocodes.findById(unLocode).get();
	}

	public void saveOrUpdate(UNLocode unLocode) {
		unLocodes.save(unLocode);
	}

	public void delete(String id) {
		unLocodes.deleteById(id);
	}
}
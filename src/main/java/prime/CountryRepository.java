package prime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CountryRepository {
	public List<String> getCountryNames() {
		List<String> countries = new ArrayList<>();

		for (String code : Locale.getISOCountries()) {
			Locale locale = new Locale("", code);
			String name = locale.getDisplayCountry();
			countries.add(name);
		}

		return countries;
	}
}

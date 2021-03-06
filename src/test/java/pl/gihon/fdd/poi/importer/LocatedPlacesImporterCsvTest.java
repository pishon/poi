package pl.gihon.fdd.poi.importer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

import pl.gihon.fdd.poi.model.LocatedPlace;

public class LocatedPlacesImporterCsvTest {

	@Test
	public void importer_importsPlaceWithAllItsProperties() {

		LocatedPlacesImporterCsv importer = new LocatedPlacesImporterCsv();

		File file = new File(this.getClass().getClassLoader().getResource("konin_assigned.csv").getFile());

		List<LocatedPlace> located = importer.importUnassignedPlaces(file);

		assertThat(located).isNotEmpty();
		LocatedPlace firstPlace = located.get(0);
		assertThat(firstPlace.getLatitude()).isEqualTo("52.240507");
		assertThat(firstPlace.getId()).isEqualTo(3);
		assertThat(firstPlace.getCity()).isEqualTo("Konin");
		assertThat(firstPlace.getLang1()).isEqualTo("lang1");
		assertThat(firstPlace.getLang2()).isEqualTo("lang2");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate expectedDate = LocalDate.parse("2011-01-01", formatter);
		LocalDate actualDate = Instant.ofEpochMilli(firstPlace.getLastMet().getTime()).atZone(ZoneId.systemDefault())
				.toLocalDate();
		assertThat(expectedDate).isEqualTo(actualDate);
		assertThat(firstPlace.getLongitude()).isEqualTo("18.277877");
		assertThat(firstPlace.getStatus()).isEqualTo("stat");
		assertThat(firstPlace.getStreetAndFlat()).isEqualTo("Leśna 41");
	}

	@Test
	public void importer_doestnImportAssignedPlaces() {
		LocatedPlacesImporterCsv importer = new LocatedPlacesImporterCsv();
		File file = new File(this.getClass().getClassLoader().getResource("konin_assigned.csv").getFile());
		List<LocatedPlace> located = importer.importUnassignedPlaces(file);

		assertThat(located).hasSize(1);

	}

}

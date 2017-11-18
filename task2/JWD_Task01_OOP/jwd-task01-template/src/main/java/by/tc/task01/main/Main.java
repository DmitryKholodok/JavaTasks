package by.tc.task01.main;

import static by.tc.task01.entity.criteria.SearchCriteria.*;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.service.ApplianceService;
import by.tc.task01.service.ServiceFactory;

public class Main {

	public static void main(String[] args) {
		Appliance appliance;

		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceService service = factory.getApplianceService();

		//////////////////////////////////////////////////////////////////

		Criteria<Oven> criteria = new Criteria<>();
		//criteriaOven = new Criteria<>();
		//criteriaOven.setApplianceType("Oven");
		criteria.add(Oven.CAPACITY, 32);

		appliance = service.find(criteria);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////


		Criteria<Oven> criteriaOven = new Criteria<>();
		criteriaOven.add(Oven.HEIGHT, "45");
		criteriaOven.add(Oven.DEPTH, 60);

		appliance = service.find(criteriaOven);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria<TabletPC> criteriaTabletPC = new Criteria<>();
		criteriaTabletPC.add(TabletPC.COLOR, "red");
		//criteriaTabletPC.add(TabletPC.DISPLAY_INCHES, 14);
		//criteriaTabletPC.add(TabletPC.MEMORY_ROM, 8000);

		appliance = service.find(criteriaTabletPC);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria<Speakers> criteriaSpeakers = new Criteria<>();
		criteriaSpeakers.add(Speakers.FREQUENCY_RANGE, "2-3.5");

		appliance = service.find(criteriaSpeakers);

		PrintApplianceInfo.print(appliance);


		//////////////////////////////////////////////////////////////////

		Criteria<VacuumCleaner> criteriaVacuumCleaner = new Criteria<>();
		criteriaVacuumCleaner.add(VacuumCleaner.WAND_TYPE, "all-in-one");
		criteriaVacuumCleaner.add(VacuumCleaner.FILTER_TYPE, "B");

		appliance = service.find(criteriaVacuumCleaner);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria<Laptop> criteriaLaptop = new Criteria<>();
		criteriaLaptop.add(Laptop.OS, "Windows");
		criteriaLaptop.add(Laptop.MEMORY_ROM, "8000");

		appliance = service.find(criteriaLaptop);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria<Refrigerator> criteriaRefr = new Criteria<>();
		criteriaRefr.setApplianceType("Refrigerator");
		criteriaRefr.add(Refrigerator.OVERALL_CAPACITY, 350.5);
		//criteriaRefr.add(Refrigerator.POWER_CONSUMPTION, 150);

		appliance = service.find(criteriaRefr);

		PrintApplianceInfo.print(appliance);




	}

}

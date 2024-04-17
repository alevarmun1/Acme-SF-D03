
package acme.features.manager.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.student1.Project;
import acme.roles.Manager;

@Controller
public class ManagerProjectController extends AbstractController<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectShowService	showService;

	@Autowired
	private ManagerProjectCreateService	createService;
	/*
	 * @Autowired
	 * private ManagerProjectUpdateService updateService;
	 */
	@Autowired
	private ManagerProjectDeleteService	deleteService;

	@Autowired
	private ManagerProjectListService	listService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);

		/*
		 * super.addBasicCommand("create", this.createService);
		 * super.addBasicCommand("update", this.updateService);
		 */
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("list-service", "list", this.listService);
	}

}


package acme.features.auditor.codeAudit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.student5.CodeAudit;
import acme.roles.Auditor;

@Controller
public class AuditorCodeAuditController extends AbstractController<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorCodeAuditListService listService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("list-service", "list", this.listService);
	}
}


package acme.features.sponsor.sponsorship;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.student4.Sponsorship;
import acme.roles.Sponsor;

@Controller
public class SponsorSponsorshipController extends AbstractController<Sponsor, Sponsorship> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorSponsorshipShowService	showService;

	@Autowired
	private SponsorSponsorshipListService	listService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addCustomCommand("list-service", "list", this.listService);
	}
}

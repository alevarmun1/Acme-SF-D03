
package acme.features.auditor.auditRecord;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.student5.AuditRecord;
import acme.entities.student5.CodeAudit;
import acme.entities.student5.Mark;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordUpdateService extends AbstractService<Auditor, AuditRecord> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository	repository;

	private Date							lowestMoment	= Date.from(Instant.parse("1999-12-31T23:00:00Z"));
	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		CodeAudit codeAudit;

		masterId = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditByAuditRecordId(masterId);
		status = codeAudit != null && !codeAudit.isPublished() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		int id;
		AuditRecord object;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark", "link");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("auditPeriodStart")) {
			Date startDate = object.getAuditPeriodStart();

			super.state(MomentHelper.isAfter(startDate, this.lowestMoment), "auditPeriodStart", "auditor.auditRecord.form.error.startDateError");
		}
		if (!super.getBuffer().getErrors().hasErrors("auditPeriodEnd")) {
			Date endDate = object.getAuditPeriodEnd();

			super.state(MomentHelper.isAfter(endDate, this.lowestMoment), "auditPeriodEnd", "auditor.auditRecord.form.error.endDateError");
		}
		if (!super.getBuffer().getErrors().hasErrors("auditPeriodEnd") && !super.getBuffer().getErrors().hasErrors("startDate"))

		{
			Date startDate = object.getAuditPeriodStart();
			Date endDate = object.getAuditPeriodEnd();

			super.state(this.isPassedOneHourAtLeast(endDate, startDate), "auditPeriodEnd", "auditor.auditRecord.form.error.notTimeEnough");
		}

	}
	private boolean isPassedOneHourAtLeast(final Date date1, final Date date2) {
		boolean res = false;
		long diffInMillies = date1.getTime() - date2.getTime();
		long diffInHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (diffInHours >= 1L)
			res = true;
		return res;
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;
		CodeAudit codeAudit;

		choices = SelectChoices.from(Mark.class, object.getMark());
		codeAudit = object.getCodeAudit();

		dataset = super.unbind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark", "link", "published");
		dataset.put("masterId", object.getCodeAudit().getId());
		dataset.put("codeAuditCode", codeAudit.getCode());
		dataset.put("mark", choices.getSelected().getKey());
		dataset.put("marks", choices);

		super.getResponse().addData(dataset);
	}
}

package com.ncema.internal.resource.v1_0;

import com.liferay.petra.function.UnsafeBiConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.MeetingResponse;
import com.ncema.resource.v1_0.MeetingResource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import topic.management.service.MeetingService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;


@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/meeting.properties",
	scope = ServiceScope.PROTOTYPE, service = MeetingResource.class
)
public class MeetingResourceImpl extends BaseMeetingResourceImpl {

	@Reference
	MeetingService _meetingService;

	@Override
	public void setContextBatchUnsafeBiConsumer(UnsafeBiConsumer<Collection<Meeting>, UnsafeFunction<Meeting, Meeting, Exception>, Exception> unsafeBiConsumer) {}

	@Override
	public Response createMeeting(Meeting meeting) throws Exception {
		return Response.ok( _meetingService.createMeeting(contextUser.getUserId(), meeting)).build();
	}

	@Override
	public Page<MeetingResponse> getMeetings(String search, Filter filter, Pagination pagination, Sort[] sorts) throws Exception {
		return _meetingService.getMeetings(contextCompany.getCompanyId(), search, filter, pagination, sorts);
	}

	@Override
	public Object getMeetingById(Long meetingId) {
		return _meetingService.getMeetingById(meetingId);
	}

	@Override
	public Response deleteMeeting(Long meetingId){
		return Response.ok(_meetingService.deleteMeeting(meetingId)).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Page<Long> getMeetingParticipants(Long meetingId) {
		List<Long> participants = _meetingService.getMeetingParticipants(meetingId);
		return Page.of(participants);
	}

	@Override
	public Response updateMeeting(Long meetingId, Meeting meeting)  {
		return Response.ok(_meetingService.updateMeeting(contextUser.getUserId(), meetingId, meeting)).build();
	}

}
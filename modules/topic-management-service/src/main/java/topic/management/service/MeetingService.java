package topic.management.service;

import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.ncema.dto.v1_0.Meeting;
import com.ncema.dto.v1_0.MeetingResponse;
import java.util.List;

public interface MeetingService {

    Object createMeeting(long userId, Meeting meeting);

    Page<MeetingResponse> getMeetings(long companyId, String search, Filter filter,
                                      Pagination pagination, Sort[] sorts);

    Object getMeetingById(long meetingId);

    Object deleteMeeting(long meetingId);

    List<Long> getMeetingParticipants(long meetingId);

    Object updateMeeting(long userId, long meetingId, Meeting meeting);

}

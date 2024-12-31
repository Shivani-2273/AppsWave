package topic.management.constants;

public class TopicConstants {
    public static final String TOPIC_OBJECT_KEY = "C_Topic";
    public static final String MEETING_OBJECT_KEY = "C_Meeting";
    public static final String PARTICIPANT_OBJECT_KEY = "C_Participants";
    public static final String ATTACHMENT_OBJECT_KEY = "C_Attachment";
    public static final String MEETING_TOPIC_RELATIONSHIP_KEY = "meetingTopics";

    public enum TopicStatus {
        APPROVED("Approved"),
        REJECTED("Rejected"),
        REVIEWED("Reviewed"),
        PROCESSED("Processed"),
        PENDING_REVISION("PendingRevision");

        private final String key;

        TopicStatus(String key) {
            this.key = key;
        }
        public String getKey() {
            return key;
        }

        public static TopicStatus fromKey(String key) {
            for (TopicStatus status : TopicStatus.values()) {
                if (status.key.equals(key)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown key: " + key);
        }

        @Override
        public String toString() {
            return key;
        }
    }


}

CREATE TABLE check_ins (
                           id bigserial NOT NULL PRIMARY KEY,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           attendee_id TEXT NOT NULL,
                           CONSTRAINT check_ins_attendeeId_fkey FOREIGN KEY (attendee_id) REFERENCES attendees (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE UNIQUE INDEX check_ins_attendeeId_key ON check_ins(attendee_id);
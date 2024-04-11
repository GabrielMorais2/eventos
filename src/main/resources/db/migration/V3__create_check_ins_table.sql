CREATE TABLE check_ins (
                           id serial NOT NULL PRIMARY KEY,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           attendeeId TEXT NOT NULL,
                           CONSTRAINT check_ins_attendeeId_fkey FOREIGN KEY (attendeeId) REFERENCES attendees (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE UNIQUE INDEX check_ins_attendeeId_key ON check_ins(attendeeId);
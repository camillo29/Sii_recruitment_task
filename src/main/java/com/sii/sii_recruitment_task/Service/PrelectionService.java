package com.sii.sii_recruitment_task.Service;

import com.sii.sii_recruitment_task.Model.Prelection;

import java.sql.Time;
import java.util.List;

public interface PrelectionService {
    List<Prelection> getPrelections();
    Prelection findById(Long id) throws Exception;
    void validatePrelection(Prelection prelection) throws Exception;
    List<Prelection> getPrelectionsByHour(Time hour);
}

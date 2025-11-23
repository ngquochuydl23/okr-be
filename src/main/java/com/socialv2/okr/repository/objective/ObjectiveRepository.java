package com.socialv2.okr.repository.objective;

import com.socialv2.okr.entities.objectives.Objective;

import java.util.UUID;

public interface ObjectiveRepository {

    Objective findById(UUID objectiveId);
}

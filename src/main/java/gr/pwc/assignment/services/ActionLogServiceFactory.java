package gr.pwc.assignment.services;

import gr.pwc.assignment.enums.ActionType;

public interface ActionLogServiceFactory {

    ActionLogService getActionLogService(ActionType actionType);
}

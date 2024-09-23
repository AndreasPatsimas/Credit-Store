package gr.pwc.assignment.services.impl.action_logs;

import gr.pwc.assignment.enums.ActionType;
import gr.pwc.assignment.services.ActionLogService;
import gr.pwc.assignment.services.ActionLogServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;

@Service
public class ActionLogServiceFactoryImpl implements ActionLogServiceFactory {

    private final EnumMap<ActionType, ActionLogService> actionLogServiceMap;

    @Autowired
    public ActionLogServiceFactoryImpl(List<ActionLogService> actionLogServices){
        actionLogServiceMap = new EnumMap<>(ActionType.class);
        actionLogServices.forEach(actionLogService ->
                actionLogServiceMap.put(actionLogService.getCorrelation(), actionLogService)
        );
    }

    @Override
    public ActionLogService getActionLogService(ActionType actionType) {
        return actionLogServiceMap.get(actionType);
    }
}

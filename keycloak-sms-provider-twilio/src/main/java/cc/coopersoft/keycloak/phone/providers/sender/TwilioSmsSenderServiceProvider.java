package cc.coopersoft.keycloak.phone.providers.sender;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import cc.coopersoft.keycloak.phone.providers.exception.MessageSendException;
import cc.coopersoft.keycloak.phone.providers.spi.FullSmsSenderAbstractService;
import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;

public class TwilioSmsSenderServiceProvider extends FullSmsSenderAbstractService {

    private static final Logger logger = Logger.getLogger(TwilioSmsSenderServiceProvider.class);
    private final String twilioPhoneNumber;

    TwilioSmsSenderServiceProvider(Scope config, String realmDisplay) {
        super(realmDisplay);
        Twilio.init(config.get("accountSId"), config.get("authToken"));
        this.twilioPhoneNumber = config.get("twilioPhoneNumber");

    }

    @Override
    public void sendMessage(String phoneNumber, String message) throws MessageSendException {

        Message msg = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message).create();

        if (msg.getStatus() == Message.Status.FAILED) {
            throw new MessageSendException(msg.getStatus().ordinal(),
                    String.valueOf(msg.getErrorCode()),
                    msg.getErrorMessage());
        }
    }

    @Override
    public void close() {
    }
}

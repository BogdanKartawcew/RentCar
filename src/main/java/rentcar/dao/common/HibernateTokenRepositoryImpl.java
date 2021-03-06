package rentcar.dao.common;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rentcar.model.PersistentLogin;

import static rentcar.propertiesenums.ControlersTexts.Constants.LOW_LOGIN;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
        implements PersistentTokenRepository {

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeUserTokens(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(LOW_LOGIN, login));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }
}

package cl.rhsso;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.storage.UserStorageProviderFactory;
import org.keycloak.storage.UserStorageProviderModel;
import org.keycloak.storage.user.ImportSynchronization;
import org.keycloak.storage.user.SynchronizationResult;

import cl.rhsso.vo.UserInfoVO;

public class AccessUserStorageProviderFactory implements UserStorageProviderFactory<AccessUserStorageProvider>, ImportSynchronization {

    private static final Logger logger = Logger.getLogger(AccessUserStorageProviderFactory.class);
    
    public static final String PROVIDER_NAME = "access-user-federation";
    protected List<ProviderConfigProperty> configMetadata;
    
    private static final String JNDI_EJB_PROVIDER = "java:global/access-user-federation-1.0.0/";
    
    @Override
    public String getId() {
        return PROVIDER_NAME;
    }
 
    @Override
    public AccessUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        
        AccessUserStorageProvider provider = null;
        try {
        	InitialContext ctx = new InitialContext();
        	
			provider = (AccessUserStorageProvider)ctx.lookup(JNDI_EJB_PROVIDER + AccessUserStorageProvider.class.getSimpleName());
			
			provider.setModel(model);
			provider.setSession(session);
		    
		} catch (NamingException e) {
			e.printStackTrace();
		}
        return provider;
    }

	@Override
	public SynchronizationResult sync(KeycloakSessionFactory sessionFactory, String realmId,
			UserStorageProviderModel model) {
		InitialContext ctx;
		
		final SynchronizationResult syncResult = new SynchronizationResult();

//		try {
//			ctx = new InitialContext();
//			AccessUserStorageProvider localProvider = (AccessUserStorageProvider)ctx.lookup(JNDI_EJB_PROVIDER + AccessUserStorageProvider.class.getSimpleName());
//			 
//			for (UserInfoVO user: localProvider.getAllUsers()) {
//				syncResult.increaseAdded();
//				logger.debug("user: "+user.toString());
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
		logger.debug("dentro de sync");
		return syncResult;
	}

	@Override
	public SynchronizationResult syncSince(Date lastSync, KeycloakSessionFactory sessionFactory, String realmId,
			UserStorageProviderModel model) {
		
		InitialContext ctx;
		
		final SynchronizationResult syncResult = new SynchronizationResult();

//		try {
//			ctx = new InitialContext();
//			AccessUserStorageProvider localProvider = (AccessUserStorageProvider)ctx.lookup(JNDI_EJB_PROVIDER + AccessUserStorageProvider.class.getSimpleName());
//			 
//			for (UserInfoVO user: localProvider.getAllUsers()) {
//				syncResult.increaseAdded();
//				logger.debug("user: "+user.toString());
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
		logger.debug("dentro de sync");
		return syncResult;
	}
}

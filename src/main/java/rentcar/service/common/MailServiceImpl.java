package rentcar.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {
}

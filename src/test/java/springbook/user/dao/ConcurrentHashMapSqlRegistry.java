package springbook.user.dao;

import springbook.user.sqlservice.UpdatableSqlRegistry;

public class ConcurrentHashMapSqlRegistry extends AbstractUpdatableSqlRegistryTest {
    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new springbook.user.sqlservice.ConcurrentHashMapSqlRegistry();
    }
}

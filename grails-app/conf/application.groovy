
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'am.neovision.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'am.neovision.UserAuthority'
grails.plugin.springsecurity.authority.className = 'am.neovision.Authority'
grails.plugin.springsecurity.securityConfigType = grails.plugin.springsecurity.SecurityConfigType.InterceptUrlMap
grails.plugin.springsecurity.interceptUrlMap = [
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/api/login',          access: ['permitAll']],
		[pattern: '/api/user/register',          access: ['permitAll']],
		[pattern: '/api/user/resetPassword',  access:['permitAll']],
		[pattern: '/api/user/changePasswordByEmailCode/**',  access:['permitAll']],
		[pattern: '/api/user/activateProfile/**',  access:['permitAll']],
		[pattern: '/api/logout',        access: ['isFullyAuthenticated()']],
		[pattern: '/api/product',    access: ['isFullyAuthenticated()']],
		[pattern: '/**',             access: ['isFullyAuthenticated()']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/api/user/resetPassword', filters: 'none'],
	[pattern: '/api/user/changePasswordByEmailCode/**', filters: 'none'],
	[pattern: '/api/user/register', filters: 'none'],
	[pattern: '/api/user/activateProfile/**', filters: 'none'],
	[pattern: '/api/**', filters:'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
	[pattern: '/**', filters:'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]

grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'
grails.plugin.springsecurity.rest.token.validation.headerName = "Authorization"
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'secret01234567890ABCDEFGHIJKLMNO'
grails.plugin.springsecurity.rest.token.storage.memcached.hosts = 'localhost:11211'
grails.plugin.springsecurity.rest.token.storage.memcached.username = ''
grails.plugin.springsecurity.rest.token.storage.memcached.password = ''
grails.plugin.springsecurity.rest.token.storage.memcached.expiration = 86400

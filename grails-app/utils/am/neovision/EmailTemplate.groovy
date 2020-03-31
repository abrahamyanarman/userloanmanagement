package am.neovision

import am.neovision.exception.CustomException

import java.nio.file.Files

class EmailTemplate {
    private String templateId
    private String template
    private Map<String,String> replacementParams

    EmailTemplate() {
    }

    EmailTemplate(String templateId) {
        this.templateId = templateId
        this.template = loadTemplate(templateId)
    }

    private String loadTemplate(String templateId){
        def classLoader = this.class.getClassLoader()
        def file = new File(classLoader.getResource("templates/$templateId").file)
        String content = new String(Files.readAllBytes(file.toPath()))
        if (content == "" || content == null)
            throw new CustomException("Couldn't read template with ID: $templateId")
        return content
    }

    String getTemplate(Map<String,String>replacements){
        def cTemplate = this.template
        if (!(cTemplate==""||cTemplate==null)){
            replacements.each { key,value ->
                cTemplate = cTemplate.replace(key,value)

            }
        }
        return cTemplate
    }
}

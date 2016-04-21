# Minimal

A minimalist project with a single Customer entity bean.

When running in the IDE it is best to install the "enhancement" plugin.
Refer to http://ebean-orm.github.io/docs/setup/enhancement#ide


## Normally

This example is very simplistic so just some notes.

- Normally entity beans extend a @Mappedsuperclass bean which has common 
properties like @Version, @WhenCreated, @WhenModified etc

- With Ebean due to it's stateless nature people often use a more "active record" extending Model and using "Finders".  Look at example-active-record for this.

- DB Migration: This example also does not have a MainDBMigration method which generates migration ddl. Look at example-active-record for this too.


#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <getopt.h>

int main(int argc, char *argv[])
{
    int opt;
    size_t len;
    char *cmd;
    int option_index = 0;

    struct option long_options[] = {
        { "file", required_argument, 0, 'f' },   
        { "dir",  required_argument, 0, 'd' }, 
        { "ext",  required_argument, 0, 'e' },   
        { 0, 0, 0, 0 }
    };

    
    while ((opt = getopt_long(argc, argv, "f:d:e:", long_options, &option_index)) != -1)
    {
        switch (opt)
        {
            case 'f':
                len = snprintf(NULL, 0, "ls -p \"%s\" | grep -v /  ", optarg) + 1;
                cmd = malloc(len);
                snprintf(cmd, len, "ls -p \"%s\" | grep -v /  ", optarg);
                system(cmd);
                free(cmd);
                break;
            case 'd':
                len = snprintf(NULL, 0, "ls -l \"%s\" | grep '^d'", optarg) + 1;
                cmd = malloc(len);
                snprintf(cmd, len, "ls -l \"%s\" | grep '^d'", optarg);
                system(cmd);
                free(cmd);
                break;
            case 'e':
                len = snprintf(NULL, 0, "ls \"%s\" | grep -e '\\.ext$' ", optarg) + 1;
                cmd = malloc(len);
                snprintf(cmd, len, "ls \"%s\" | grep -e '\\.ext$' ", optarg);
                system(cmd);
                free(cmd);
                break;
            default: /* '?' */
                fprintf(stderr, "Usage: %s [-f] [-d] [-e] directory\n",
                        argv[0]);
                exit(EXIT_FAILURE);
        }
    }

    return EXIT_SUCCESS;
}

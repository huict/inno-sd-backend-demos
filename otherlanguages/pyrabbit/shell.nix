let
   nixpkgs = fetchTarball "https://github.com/NixOS/nixpkgs/tarball/nixos-23.11";
   pkgs = import nixpkgs { config = {}; overlays = []; };
 in

 pkgs.mkShell {
   packages = with pkgs; [
     python3
     pipenv
   ];
   PIPENV_VENV_IN_PROJECT=true;

   shellHook=''
    pipenv shell
   '';
 }